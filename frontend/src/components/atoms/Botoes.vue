<template lang='pug'>
	v-btn.mr-4.botao(:disabled="disabled", color='#3949AB', @click="actionClick") {{label}}
</template>

<script>
	export default {
		name: 'Botao',
		props:{
			disabled:{
				type:Boolean,
				default: false
			},
			label:{
				type:String,
				required: true

			},

		},
		methods:{
			actionClick: function(){
				this.$emit("clickBotao");
			},
		}
	}
</script>

<style scoped>
	.botao{
		color: white !important;
	}
</style>